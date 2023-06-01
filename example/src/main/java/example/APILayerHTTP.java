package example;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;

import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APILayerHTTP {
    // Handling post request
    @PostMapping("/EnterDetails")

    String insert(@RequestBody Details ob) {
        // Storing the incoming data in the list
        Details.Data.add(new Details(ob.number, ob.name));

        // Iterating using foreach loop
        for (Details obd : Details.Data) {
            System.out.println(obd.name + " " + ob.number);
        }
        return "Data Inserted";
    }

    public class PetNameResponse {
        private List<String> petNames;

        public PetNameResponse(List<String> petNames) {
            this.petNames = petNames;
        }

        public List<String> getPetNames() {
            return petNames;
        }

        public void setPetNames(List<String> petNames) {
            this.petNames = petNames;
        }
    }

    @PostMapping("/generatePetName")
    public PetNameResponse generatePetName(@RequestBody AnimalRequest animalRequest) {
        String token = System.getenv("OPENAI_TOKEN");
        OpenAiService service = new OpenAiService(token);
        System.out.println("\nCreating pet names...");
        String prompt = GeneratePrompt.generatePromptOne(animalRequest.getAnimal());
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt(prompt)
                .temperature(0.6)
                .build();
        List<String> gptResponse = new ArrayList<>();
        service.createCompletion(completionRequest).getChoices().forEach(choice -> {
            gptResponse.add(choice.getText());
            System.out.println(choice.getText());
        });
        service.shutdownExecutor();
        return new PetNameResponse(gptResponse);
    }

    @GetMapping("/codeTutor")
    public List<String> generateTutorResponse() {
        String token = System.getenv("OPENAI_TOKEN");
        OpenAiService service = new OpenAiService(token);
        System.out.println("\nCreating response from tutor...");

        final List<ChatMessage> messages = new ArrayList<>();

        // Generate the prompt message
        String prompt = GeneratePrompt.generatePromptTwo();

        // Add the system message (prompt)
        final ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), prompt);
        messages.add(systemMessage);

        // Add a user message to provide additional instructions or context
        final ChatMessage userMessage = new ChatMessage(ChatMessageRole.USER.value(),
                "Can you provide an example of refactoring the Order class?");
        messages.add(userMessage);
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .n(1)
                .maxTokens(50)
                .logitBias(new HashMap<>())
                .build();

        // List<String> gptResponse = new ArrayList<>();
        // service.streamChatCompletion(chatCompletionRequest)
        // .doOnError(Throwable::printStackTrace)
        // .blockingSubscribe(completion -> {
        // completion.getChoices().forEach(choice -> {
        // gptResponse.add(choice.getMessage().getContent());
        // System.out.println(choice.getMessage().getContent());
        // });
        // });

        List<String> gptResponse = new ArrayList<>();
        service.streamChatCompletion(chatCompletionRequest)
                .doOnError(Throwable::printStackTrace)
                .blockingSubscribe(completion -> {
                    completion.getChoices().forEach(choice -> {
                        String response = String.join("", choice.getMessage().getContent());
                        gptResponse.add(response);
                        System.out.println(response);
                    });
                });

        String completeResponse = String.join("", gptResponse);
        System.out.println(completeResponse);

        service.shutdownExecutor();
        return gptResponse;
    }

    // working...
    // @PostMapping("/codeTutor")
    // public String generateTutorResponse() {
    // String token = System.getenv("OPENAI_TOKEN");
    // OpenAiService service = new OpenAiService(token);
    // System.out.println("\nCreating response by tutor...");
    // String prompt = GeneratePrompt.generatePromptTwo();
    // CompletionRequest completionRequest = CompletionRequest.builder()
    // .model("text-davinci-003")
    // .prompt(prompt)
    // .temperature(0.6)
    // .build();
    // String gptResponse =
    // service.createCompletion(completionRequest).

    // service.shutdownExecutor();
    // return gptResponse;
    // }

    // The code below works (GET Request)
    // @GetMapping("/gpt3")
    // public List<String> Gpt3Api() {
    // // original code from the library
    // String token = System.getenv("OPENAI_TOKEN");
    // OpenAiService service = new OpenAiService(token);

    // System.out.println("\nCreating completion...");
    // CompletionRequest completionRequest = CompletionRequest.builder()
    // .model("ada")
    // .prompt("Somebody once told me the world is gonna roll me")
    // .echo(true)
    // .user("testing")
    // .n(3)
    // .build();

    // List<String> gptResponse = new ArrayList<>();
    // service.createCompletion(completionRequest).getChoices().forEach(choice -> {
    // gptResponse.add(choice.getText());
    // System.out.println(choice.getText());
    // });
    // service.shutdownExecutor();
    // return gptResponse;
    // }
}

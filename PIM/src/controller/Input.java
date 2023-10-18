package controller;

public class Input {
    private String input;

    /**
     * Input Contract
     * @param input: the input of user
     */
    public Input(String input) {
        this.input = input;
    }

    /**
     * Get Input Function
     * @return String: the input of user
     */
    public String getInput() {
        return input;
    }

    /**
     * Set Input Function
     * @param input: the input of user
     */
    public void setInput(String input) {
        this.input = input;
    }
}

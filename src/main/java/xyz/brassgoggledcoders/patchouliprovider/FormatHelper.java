package xyz.brassgoggledcoders.patchouliprovider;

import net.minecraft.client.settings.KeyBinding;

public class FormatHelper {
    public static String clearFormatting(){
        return wrapTextInFormattingCode("");
    }

    public static String lineBreak(){
        return wrapTextInFormattingCode("br");
    }

    public static String newParagraph(){
        return wrapTextInFormattingCode("br2");
    }

    public static String bulletedList(int nestedLevel){
        return wrapTextInFormattingCode(String.valueOf(nestedLevel));
    }

    public static String hexCodeColor(int color){
        return wrapTextInFormattingCode("#" + Integer.toHexString(color));
    }

    public static String colorCode(char lowercaseHexValue){
        return wrapTextInFormattingCode(String.valueOf(lowercaseHexValue));
    }

    public static String formatCode(char formatCode){
        return wrapTextInFormattingCode(String.valueOf(formatCode));
    }

    public static String internalLink(String entryID){
        return wrapTextInFormattingCode("l:" + entryID);
    }

    public static String internalLink(String entryID, String anchorName){
        return wrapTextInFormattingCode("l:" + entryID + "#" + anchorName);
    }

    public static String externalLink(String link){
        return wrapTextInFormattingCode("l" + link);
    }

    public static String endLink(){
        return wrapTextInFormattingCode("/l");
    }

    public static String playerName(){
        return wrapTextInFormattingCode("playername");
    }

    public static String keyValueOfKeybind(KeyBinding keyBinding){
        return wrapTextInFormattingCode("k:" + keyBinding.getKeyDescription());
    }

    public static String tooltip(String tooltip){
        return wrapTextInFormattingCode("t:" + tooltip);
    }

    public static String endTooltip(){
        return wrapTextInFormattingCode("/t");
    }

    /**
     * Sends the command to the server when text with formatting is clicked
     * @param command The command to be sent to the server, without the "/" at the beginning
     * @return The text formatting code for sending a command to the server
     */
    public static String sendCommandToServer(String command){
        return wrapTextInFormattingCode("c:/" + command);
    }

    public static String endCommand(){
        return wrapTextInFormattingCode("/c");
    }

    private static String wrapTextInFormattingCode(String text){
        return String.format("$(%s)", text);
    }
}

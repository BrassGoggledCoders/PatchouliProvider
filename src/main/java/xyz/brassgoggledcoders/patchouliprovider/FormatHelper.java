package xyz.brassgoggledcoders.patchouliprovider;

import net.minecraft.client.settings.KeyBinding;

public class FormatHelper {
    public static String clearFormatting() {
        return wrapTextInFormattingCode("");
    }

    public static String lineBreak() {
        return wrapTextInFormattingCode("br");
    }

    public static String newParagraph() {
        return wrapTextInFormattingCode("br2");
    }

    public static String bulletedList(int nestedLevel, String text) {
        return wrapTextInFormattingCode(String.valueOf(nestedLevel)) + text + clearFormatting();
    }

    public static String hexCodeColor(int color, String text) {
        return wrapTextInFormattingCode("#" + Integer.toHexString(color)) + text + clearFormatting();
    }

    public static String colorCode(char lowercaseHexValue, String text) {
        return wrapTextInFormattingCode(String.valueOf(lowercaseHexValue)) + text + clearFormatting();
    }

    public static String formatCode(char formatCode, String text) {
        return wrapTextInFormattingCode(String.valueOf(formatCode))  + text + clearFormatting();
    }

    public static String internalLink(String entryID, String text) {
        return wrapTextInFormattingCode("l:" + entryID) + text + endLink();
    }

    public static String internalLink(String entryID, String anchorName, String text) {
        return wrapTextInFormattingCode("l:" + entryID + "#" + anchorName) + text + endLink();
    }

    public static String externalLink(String link, String text) {
        return wrapTextInFormattingCode("l:" + link) + text + endLink();
    }

    public static String endLink() {
        return wrapTextInFormattingCode("/l");
    }

    public static String playerName() {
        return wrapTextInFormattingCode("playername");
    }

    public static String keyValueOfKeybind(KeyBinding keyBinding) {
        return wrapTextInFormattingCode("k:" + keyBinding.getKeyDescription());
    }

    public static String tooltip(String tooltip, String text) {
        return wrapTextInFormattingCode("t:" + tooltip) + text + endTooltip();
    }

    public static String endTooltip() {
        return wrapTextInFormattingCode("/t");
    }

    /**
     * Sends the command to the server when text with formatting is clicked
     *
     * @param command The command to be sent to the server, without the "/" at the beginning
     * @return The text formatting code for sending a command to the server
     */
    public static String sendCommandToServer(String command, String text) {
        return wrapTextInFormattingCode("c:/" + command) + text + endCommand();
    }

    public static String endCommand() {
        return wrapTextInFormattingCode("/c");
    }

    private static String wrapTextInFormattingCode(String text) {
        return String.format("$(%s)", text);
    }
}

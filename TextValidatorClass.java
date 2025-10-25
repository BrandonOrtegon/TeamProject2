public class TextValidator {

    public static boolean validateTitleOrAuthor(String text) {
        if (text == null || text.trim().isEmpty()) return false;
        return text.matches("[a-zA-Z\\s\\-]+");
    }

    public static boolean validateISBN(String isbn) {
        if (isbn == null) return false;
        return isbn.matches("978-\\d{10}") || isbn.matches("\\d{13}");
    }

    public static String normalizeText(String text) {
        if (text == null) return "";
        text = text.trim().toLowerCase();
        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1))
                      .append(" ");
            }
        }
        return result.toString().trim();
    }

    public static String removeSpecialCharacters(String text) {
        if (text == null) return "";
        StringBuilder cleaned = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetterOrDigit(c) || Character.isWhitespace(c) || c == '-') {
                cleaned.append(c);
            }
        }
        return cleaned.toString();
    }

    public static String extractKeywords(String title) {
        if (title == null) return "";
        title = title.toLowerCase();
        String[] words = title.split("\\s+");
        StringBuilder keywords = new StringBuilder();
        for (String word : words) {
            if (!word.equals("the") && !word.equals("a") && !word.equals("of") && !word.equals("and")) {
                keywords.append(word).append(" ");
            }
        }
        return keywords.toString().trim();
    }
}
// method call
//String cleanTitle = TextValidator.normalizeText(userInputTitle);
//boolean validISBN = TextValidator.validateISBN(userInputISBN);


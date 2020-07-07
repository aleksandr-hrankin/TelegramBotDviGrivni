import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendSticker;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.stickers.Sticker;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    private static final String TOKEN = "1359617583:AAF_NWy47jV24Ic1UqXjp_YepxT91ZTwMFk";
    private static final String USERNAME = "DviGrivniBot";

    public String getBotUsername() {
        return USERNAME;
    }

    public String getBotToken() {
        return TOKEN;
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendStkr(Message message) {
        SendSticker sticker = new SendSticker();
        sticker.setChatId(message.getChatId());
        sticker.setSticker("CAACAgIAAxkBAAEBBBFfBFxvRfZfBhs9_3_VR1Pgf90ZGAACyAADXCdYIgiOAs8QkeODGgQ");
        try {
            sendSticker(sticker);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        if (message != null) {
            if (message.hasText()) {
                switch (message.getText()) {
                    case "/help": sendMsg(message, "Даю две грывни");
                        break;
                }
            } else {
                Sticker sticker = message.getSticker();
                if (sticker != null) {
                    if (sticker.getSetName().equals("MemnoeRagu") && sticker.getFileSize() == 21544) {
                        sendMsg(message, getRandomAnswer());
                        sendStkr(message);
                    }
                }
            }
        }
    }

    private String getRandomAnswer() {
        String[] answers = new String[] {
                "Вот тебе", "Не благодари", "Последний раз", "От души", "Держи братик", "Заебал",
                "Ну боже, сколько можно", "От сердца", "Слава Украине.", "Ты думаешь я их печатаю?",
                "Записал тебя в список должников", "Позже вернёшь", "Можешь не возвращать", "Бомж ебаный",
                "Ради чего я существую", "Я слышал тебе не хватает? Держи", "Тут всё бомжи, по этому держи",
                "Ну кто если не я", "Убейте меня", "Спасссите, я устал", "У меня у самого осталось 2 грывни",
                "ТЫ ж вернёшь, да?", "Мама говорит что я зря это делаю", "Мы теперь друзья?"
        };

        int random = 0 + (int) (Math.random() * 24);

        return answers[random];
    }
    //deploy
}






















//    private void setButtons(SendMessage sendMessage) {
//        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//        sendMessage.setReplyMarkup(keyboardMarkup);
//        keyboardMarkup.setSelective(true);
//        keyboardMarkup.setResizeKeyboard(true);
//        keyboardMarkup.setOneTimeKeyboard(false);
//
//        List<KeyboardRow> keyboardRowList = new ArrayList<>();
//
//        KeyboardRow keyboardFirstRow = new KeyboardRow();
//        keyboardFirstRow.add(new KeyboardButton("/2"));
//        keyboardFirstRow.add(new KeyboardButton("/help"));
//        keyboardFirstRow.add(new KeyboardButton("/setting"));
//
//        keyboardRowList.add(keyboardFirstRow);
//
//        keyboardMarkup.setKeyboard(keyboardRowList);
//    }
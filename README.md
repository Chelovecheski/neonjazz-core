# 🎷 NeonJazz Core - Сердце цифрового подполья
*Каждый бит звучит, как саксофон в неоновом тумане.*

Добро пожаловать в **NeonJazz Core** – фреймворк, на котором держатся все механизмы **[Quantum Gate](EmptyLink)**. Это не просто код – это ритм, задающий тон нашей неоновой симфонии. Если вы здесь, значит, у вас все-таки есть какие-либо грандиозные планы на свой проект.

---

## 🔧 О проекте
**NeonJazz Core** - это небольшой фреймворк, который позволяет удобно и быстро создавать и запускать ботов для серверов основаных на ***Quantum Gate***.

## 📡 Текущая версия
*1.0.0-alpha.2* (Первая альфа-версия. Возможны баги и проблемы. Не рекомендуется к использованию для создания полноценного бота)

## ✈️ Установка зависимости
### Maven
```pom.xml
<dependency>
  <groupId>org.chelobyte</groupId>
  <artifactId>neonjazz-core</artifactId>
  <version>1.0.0-alpha.1</version>
</dependency>
```
## 📌 Использование
### Конфигурация проекта
В директории `/resources` создайте **два обязательных файла**:
```.env
#.env

TOKEN_NAME=BotToken
```
```application.properties
#application.properties

token = TOKEN_NAME
guild.id = GUILD_ID
```

### Запуск базового приложения
```Java
import org.chelobyte.neojazzcore.application.ApplicationStarter;

public class BasicBot {
    public static void main(String[] args) {
        ApplicationStarter applicationStarter = new ApplicationStarter();
        applicationStarter.start();
    }
}
```

### Добавление интентов и обработчиков событий
```Java
import org.chelobyte.neojazzcore.application.ApplicationGetter;
import org.chelobyte.neojazzcore.application.ApplicationStarter;
import org.chelobyte.neojazzcore.listener.OnSlashCommandListener;

import net.dv8tion.jda.api.requests.GatewayIntent;

public class BotWithIntentsAndEventListeners {
    public static void main(String[] args) {
        ApplicationStarter applicationStarter = new ApplicationStarter();

        /**
         * Включает интенты
         * @param GatewayIntent... intents
         */
        applicationStarter
                .enableIntents(GatewayIntent.AUTO_MODERATION_CONFIGURATION);
        /**
         * Добавляет обработчики событий
         * @param Object... listeners
         */
        applicationStarter.addEventListeners(ApplicationGetter.getBean(
                "slashCommandsListener", OnSlashCommandListener.class));
        // Закрывает контекст
        ApplicationGetter.closeContext();

        applicationStarter.start();
    }
}
```
#### Типы обработчиков событий
На данный момент есть всего один тип обработчика событий:
- `OnSlashCommandListener.class` - `slashCommandsListener` (Выбрасывает `org.chelobyte.neojazzcore.exception.CommandNotFound.class` исключение, если команда, которую вызвал пользователь не была найдена);

### Создание slash-команды и добавление ее
```Java
import java.util.List;

import org.chelobyte.neojazzcore.listener.command.slash.SlashCommand;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class SomeSlashCommand extends SlashCommand {
    /**
     * Конструктор создания команды
     * @param name        Название команды (например, "ban")
     * @param description Описание команды (например, "Блокирует участника сервера")
     * @param options     Список из опций команды (например, "List.of(new OptionData(OptionType.STRING, "reason", "Причина блокировки"))")
     * @param permissions Список прав доступа для вызова команды (например, "List.of(Permission.BAN_MEMBERS)")
    */
    public SomeSlashCommand() {
        super(name, description, options, permissions);
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        // Выполение команды
    }
}
```
```Java
import org.chelobyte.neojazzcore.application.ApplicationGetter;
import org.chelobyte.neojazzcore.application.ApplicationStarter;
import org.chelobyte.neojazzcore.listener.OnSlashCommandListener;
import org.chelobyte.neojazzcore.listener.factory.SlashCommandsFactory;

public class BotWithSlashCommand {
    public static void main(String[] args) {
        ApplicationStarter applicationStarter = new ApplicationStarter();

        /**
         * Добавляет slash-команды
         * @param SlashCommand... commands
         */
        SlashCommandsFactory.addSlashCommands(new Command());

        applicationStarter.addEventListeners(ApplicationGetter.getBean(
                "slashCommandsListener", OnSlashCommandListener.class));
        ApplicationGetter.closeContext();

        applicationStarter.start();
    }
}
```

### Получение цветов, которые используются на сервере
Цвета получаются из **enum-метода** `org.chelobyte.neojazzcore.util.Color.getValue()`.

### Ссылки на дополнительные ресурсы
- [Quantum Gate](DeletedLink)
- [JDA документация](https://jda.wiki/)
package guru.qa.config;

@org.aeonbits.owner.Config.Sources("classpath:properties/config.properties")
public interface Config extends org.aeonbits.owner.Config {

    @Key("id")
    int getId();

    @Key("idName")
    String getIdName();

    @Key("newUserName")
    String getNewUserName();

    @Key("newUserJob")
    String getNewUserJob();

    @Key("regEmail")
    String getRegEmail();

    @Key("regPassword")
    String getRegPassword();

    @Key("regToken")
    String getRegToken();

    @Key("pantone")
    String getPantone();
}

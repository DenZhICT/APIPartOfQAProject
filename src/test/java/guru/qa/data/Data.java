package guru.qa.data;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:properties/data.properties")
public interface Data extends Config {

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

package be.thomasmore.graduaten.diceroll.helper;

import be.thomasmore.graduaten.diceroll.entity.User;
import be.thomasmore.graduaten.diceroll.objects.DownloadableUser;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.modelmapper.ModelMapper;

import java.io.File;

public class Converter {

    public static File convertUserToXML(User user) {
        // Instantiate required classes
        XmlMapper xmlMapper = new XmlMapper();
        ModelMapper modelMapper = new ModelMapper();

        // Map user attributes to downloadable format
        DownloadableUser downloadableUser = new DownloadableUser();
        modelMapper.map(user, downloadableUser);

        // Set dates (not automatically mapped
        downloadableUser.setBirthdate(user.getBirthdate().toString());
        downloadableUser.setTimestampCreated(user.getCreated().toString());

        // Convert user object to XML
        try { xmlMapper.writeValue(new File("UserID_" + user.getUserId() + "_UserData.xml"), downloadableUser); }
        catch (Exception ex) {}

        // Return XML file
        return new File("UserID_" + user.getUserId() + "_UserData.xml");
    }
}

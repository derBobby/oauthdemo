package eu.planlos.oauthdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/now")
public class NowController {
    @GetMapping
    public String getCurrentDateTime() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Format the date and time as a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Return the formatted date and time
        return now.format(formatter);
    }

    @PostMapping("/post")
    public String getCurrentDateTimePost() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Format the date and time as a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Return the formatted date and time
        return now.format(formatter);
    }

}

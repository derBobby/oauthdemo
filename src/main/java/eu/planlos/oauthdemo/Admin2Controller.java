package eu.planlos.oauthdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin2")
public class Admin2Controller {

    @GetMapping
    public String get() {
        return "Hello, admin2!";
    }
}
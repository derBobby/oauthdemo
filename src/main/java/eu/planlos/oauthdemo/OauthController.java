package eu.planlos.oauthdemo;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oauth")
public class OauthController {

    @GetMapping
    public String oauthInfos(Model model, SecurityContext context) {

        model.addAttribute("authorities", context.getAuthentication().getAuthorities());
        model.addAttribute("credentials", context.getAuthentication().getCredentials());
        model.addAttribute("details", context.getAuthentication().getDetails());
        model.addAttribute("principal", context.getAuthentication().getPrincipal());
        model.addAttribute("name", context.getAuthentication().getName());

        return "oauth";
    }
}

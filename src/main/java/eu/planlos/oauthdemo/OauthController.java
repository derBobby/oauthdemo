package eu.planlos.oauthdemo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oauth")
public class OauthController {

    @GetMapping
    public String oauthInfos(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("authorities", authentication.getAuthorities());
        model.addAttribute("credentials", authentication.getCredentials());
        model.addAttribute("details", authentication.getDetails());
        model.addAttribute("principal", authentication.getPrincipal());
        model.addAttribute("name", authentication.getName());

        return "oauth";
    }
}

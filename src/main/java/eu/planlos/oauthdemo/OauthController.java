package eu.planlos.oauthdemo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/oauth")
public class OauthController {

    @GetMapping
    public String oauthInfos(Model model, Principal principal) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("authorities", authentication.getAuthorities());
        model.addAttribute("details", authentication.getDetails());
        model.addAttribute("principal", authentication.getPrincipal());
        model.addAttribute("principalAutowired", principal);
        model.addAttribute("name", authentication.getName());

        DefaultOidcUser user = (DefaultOidcUser) authentication.getPrincipal();

        model.addAttribute("1", user.getFullName());
        model.addAttribute("2", user.getUserInfo());
        model.addAttribute("3", user.getName());
        model.addAttribute("4", user.getFamilyName());
        model.addAttribute("5", user.getClaims());
        model.addAttribute("6", user.getGivenName());
        model.addAttribute("7", user.getMiddleName());
        model.addAttribute("8", user.getNickName());
        model.addAttribute("9", user.getPreferredUsername());
        model.addAttribute("10", user.getClaims());
        model.addAttribute("11", user.getIdToken());
        model.addAttribute("12", user.getAccessTokenHash());
        model.addAttribute("13", user.getAddress());
        model.addAttribute("14", user.getAttributes());
        model.addAttribute("15", user.getAuthenticatedAt());
        model.addAttribute("16", user.getAuthenticationMethods());
        model.addAttribute("17", user.getAuthorities());
        model.addAttribute("18", user.getAuthorizedParty());
        model.addAttribute("19", user.getBirthdate());
        model.addAttribute("20", user.getEmail());
        model.addAttribute("21", user.getEmailVerified());
        model.addAttribute("22", user.getExpiresAt());
        model.addAttribute("23", user.getGender());
        model.addAttribute("24", user.getIssuedAt());
        model.addAttribute("25", user.getIssuer());
        model.addAttribute("26", user.getLocale());
        model.addAttribute("27", user.getPhoneNumber());
        model.addAttribute("28", user.getPhoneNumberVerified());
        model.addAttribute("29", user.getPicture());
        model.addAttribute("30", user.getProfile());
        model.addAttribute("31", user.getSubject());
        model.addAttribute("32", user.getUpdatedAt());
        model.addAttribute("33", user.getWebsite());
        return "oauth";
    }
}

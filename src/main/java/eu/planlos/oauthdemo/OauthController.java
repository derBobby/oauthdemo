package eu.planlos.oauthdemo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/oauth")
public class OauthController {

    @GetMapping
    public String oauthInfos(Model model, Principal principal, @AuthenticationPrincipal OAuth2User oauthUser) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authorities", authentication.getAuthorities());
        model.addAttribute("details", authentication.getDetails());
        model.addAttribute("principal", authentication.getPrincipal());
        model.addAttribute("principalAutowired", principal);
        model.addAttribute("name", authentication.getName());


        DefaultOidcUser user = (DefaultOidcUser) authentication.getPrincipal();
        model.addAttribute("v1", user.getFullName());
        model.addAttribute("v2", user.getUserInfo());
        model.addAttribute("v3", user.getName());
        model.addAttribute("v4", user.getFamilyName());
        model.addAttribute("v5", user.getClaims());
        model.addAttribute("v6", user.getGivenName());
        model.addAttribute("v7", user.getMiddleName());
        model.addAttribute("v8", user.getNickName());
        model.addAttribute("v9", user.getPreferredUsername());
        model.addAttribute("v10", user.getClaims());
        model.addAttribute("v11", user.getIdToken());
        model.addAttribute("v12", user.getAccessTokenHash());
        model.addAttribute("v13", user.getAddress());
        model.addAttribute("v14", user.getAttributes());
        model.addAttribute("v15", user.getAuthenticatedAt());
        model.addAttribute("v16", user.getAuthenticationMethods());
        model.addAttribute("v17", user.getAuthorities());
        model.addAttribute("v18", user.getAuthorizedParty());
        model.addAttribute("v19", user.getBirthdate());
        model.addAttribute("v20", user.getEmail());
        model.addAttribute("v21", user.getEmailVerified());
        model.addAttribute("v22", user.getExpiresAt());
        model.addAttribute("v23", user.getGender());
        model.addAttribute("v24", user.getIssuedAt());
        model.addAttribute("v25", user.getIssuer());
        model.addAttribute("v26", user.getLocale());
        model.addAttribute("v27", user.getPhoneNumber());
        model.addAttribute("v28", user.getPhoneNumberVerified());
        model.addAttribute("v29", user.getPicture());
        model.addAttribute("v30", user.getProfile());
        model.addAttribute("v31", user.getSubject());
        model.addAttribute("v32", user.getUpdatedAt());
        model.addAttribute("v33", user.getWebsite());


        model.addAttribute("o1", oauthUser.getName());
        model.addAttribute("o2", oauthUser.getAttributes());
        model.addAttribute("o3", oauthUser.getAuthorities());

        return "oauth";
    }
}
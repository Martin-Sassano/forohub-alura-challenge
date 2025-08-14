package com.forohub.alura.foro.Controller;

import com.forohub.alura.foro.Dto.LoginDTO;
import com.forohub.alura.foro.Dto.TokenDTO;
import com.forohub.alura.foro.Model.Usuario;
import com.forohub.alura.foro.Security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginDTO loginDTO) {
        var authToken = new UsernamePasswordAuthenticationToken(
                loginDTO.email(), loginDTO.password()
        );
        var authentication = authManager.authenticate(authToken);
        var jwt = tokenService.generarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(jwt));
    }
}
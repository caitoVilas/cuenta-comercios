package com.comercios.cuentaComercios.security.jwt;

import com.comercios.cuentaComercios.security.UsuarioPrincipal;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    public String generateToken(Authentication authentication){
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        return Jwts.builder()
                .signWith(getKey(secret))
                .setSubject(usuarioPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .claim("roles", getRoles(usuarioPrincipal))
                .compact();
    }

    public String getusernameFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(getKey(secret)).build().parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(getKey(secret)).build().parseClaimsJws(token).getBody();
            return true;
        }catch (UnsupportedJwtException e){
            logger.error("token no soportado");
        }catch (ExpiredJwtException e){
            logger.error("token vencido");
        }catch (MalformedJwtException e){
            logger.error("token mal formado");
        }catch (SignatureException e){
            logger.error("error en la firma del token");
        }catch (IllegalArgumentException e){
            logger.error("argumentpo erroneo");
        }
        return false;
    }

    public List<String> getRoles(UsuarioPrincipal usuarioPrincipal){
        return usuarioPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    public Key getKey(String secret){
        byte[] secretBytes = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}

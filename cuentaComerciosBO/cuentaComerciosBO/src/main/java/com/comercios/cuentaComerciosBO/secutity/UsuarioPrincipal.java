package com.comercios.cuentaComerciosBO.secutity;

import com.comercios.cuentaComerciosBO.entity.SucursalDeRadicacion;
import com.comercios.cuentaComerciosBO.entity.UsuarioBO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UsuarioPrincipal implements UserDetails {
    private String username;
    private String password;
    private SucursalDeRadicacion sucursalDeRadicacion;
    private Collection<? extends GrantedAuthority> authorities;

    public static UsuarioPrincipal build(UsuarioBO usuario){
        Collection<GrantedAuthority> authorities =
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolName()))
                        .collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getUsername(), usuario.getPassword(), usuario.getSucursalDeRadicacion(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public SucursalDeRadicacion getSucursalDeRadicacion() {
        return sucursalDeRadicacion;
    }
}

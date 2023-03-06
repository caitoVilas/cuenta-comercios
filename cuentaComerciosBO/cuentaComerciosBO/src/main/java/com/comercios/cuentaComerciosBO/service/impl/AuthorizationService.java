package com.comercios.cuentaComerciosBO.service.impl;

import com.comercios.cuentaComerciosBO.constants.ErrorMsg;
import com.comercios.cuentaComerciosBO.constants.SuccessMsg;
import com.comercios.cuentaComerciosBO.entity.Documentacion;
import com.comercios.cuentaComerciosBO.entity.Permiso;
import com.comercios.cuentaComerciosBO.entity.Rol;
import com.comercios.cuentaComerciosBO.exception.BadRequestException;
import com.comercios.cuentaComerciosBO.repository.DocumentacionRepository;
import com.comercios.cuentaComerciosBO.repository.PermisoRepository;
import com.comercios.cuentaComerciosBO.repository.RolRepository;
import com.comercios.cuentaComerciosBO.secutity.jwt.JwtFilter;
import com.comercios.cuentaComerciosBO.secutity.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
public class AuthorizationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationService.class);
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PermisoRepository permisoRepository;
    @Autowired
    DocumentacionRepository documentacionRepository;
    @Value("${jwt.secret}")
    private String secret;

    public boolean authorizeDocumentacion(String rolName, Long permisoId, Documentacion documento){
        logger.info("obteniendo autorizacion...");
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                .getRequest();
        String token = jwtFilter.getToken(request);
        Claims claims = Jwts.parserBuilder().setSigningKey(jwtProvider.getKey(secret)).build()
                .parseClaimsJws(token).getBody();
        Object rolObject = claims.get("roles");
        Object sucursalObject = claims.get("sucursalDeRadicacion");
        Object r = ((ArrayList) rolObject).get(0);
        Rol rol = rolRepository.findByRolName(r.toString());
        Permiso permiso = permisoRepository.findById(permisoId).get();
            if (rol.getPermisos().contains(permiso) && rol.getRolName().equals(rolName)){
                if (!documento.getComercio().getSucursalDeRadicacion().getCodigoSucursal().equals(
                        ((LinkedHashMap) sucursalObject).get("codigoSucursal")
                )){
                    logger.error(ErrorMsg.NO_SUCURSAL);
                    throw new BadRequestException(ErrorMsg.NO_SUCURSAL);
                }
                logger.info(SuccessMsg.AUTORIZED);
                return true;
            }
        logger.error(ErrorMsg.UNAUTORIZED);
        return false;
    }
}

package com.comercios.cuentaComerciosBO.mapper;

import com.comercios.cuentaComerciosBO.dto.UsuarioAppDTO;
import com.comercios.cuentaComerciosBO.entity.Usuario;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioAppMapper {
    UsuarioAppDTO usuarioToUsuarioAppDTO(Usuario request);
    List<UsuarioAppDTO> usuarioListToUsuarioAppDTOList(List<Usuario> request);
}

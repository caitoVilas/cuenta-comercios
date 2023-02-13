package com.comercios.cuentaComercios.mapper;

import com.comercios.cuentaComercios.dto.RolDTO;
import com.comercios.cuentaComercios.entity.Rol;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolMapper {
    RolDTO rolToRolDTO(Rol request);
    List<RolDTO> rolListToRolDTOList(List<Rol> request);
}

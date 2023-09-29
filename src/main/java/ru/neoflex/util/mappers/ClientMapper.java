package ru.neoflex.util.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.neoflex.models.Client;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClientMapper {
    Client clientDtoToClient(ru.neoflex.dtos.Client clientDto);
    ru.neoflex.dtos.Client clientToClientDto(Client client);
}

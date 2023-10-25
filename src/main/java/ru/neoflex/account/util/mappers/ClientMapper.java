package ru.neoflex.account.util.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.neoflex.account.models.client.Client;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClientMapper {
    Client clientDtoToClient(ru.neoflex.account.dtos.Client clientDto);
    ru.neoflex.account.dtos.Client clientToClientDto(Client client);
}

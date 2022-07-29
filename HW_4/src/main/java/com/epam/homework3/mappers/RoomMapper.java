package com.epam.homework3.mappers;

import com.epam.homework3.controller.dto.RoomDto;
import com.epam.homework3.model.entity.Room;
import com.epam.homework3.model.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomDto roomToRoomDto(Room room);

    Room roomDtoToRoom(RoomDto roomDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Room updateRoom(@MappingTarget Room room, RoomDto roomDto);
}

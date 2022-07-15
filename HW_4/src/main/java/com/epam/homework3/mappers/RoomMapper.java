package com.epam.homework3.mappers;

import com.epam.homework3.controller.dto.RoomDto;
import com.epam.homework3.model.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    RoomDto roomToRoomDto(Room room);

    Room roomDtoToRoom(RoomDto roomDto);
}

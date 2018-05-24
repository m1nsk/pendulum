package com.minsk.pendulum.DTO;

import com.minsk.pendulum.DTO.channel.ChannelDto;
import com.minsk.pendulum.DTO.device.DeviceDto;
import com.minsk.pendulum.DTO.image.ImageEntityDto;
import com.minsk.pendulum.DTO.message.MessageDto;
import com.minsk.pendulum.DTO.message.MessageDtoFull;
import com.minsk.pendulum.DTO.user.UserCreateDto;
import com.minsk.pendulum.DTO.user.UserDto;
import com.minsk.pendulum.DTO.user.UserFullDto;
import com.minsk.pendulum.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DtoUtils {
    @Autowired
    private ModelMapper modelMapper;

    public UserFullDto convertToFullDto(User user) {
        UserFullDto userDto = modelMapper.map(user, UserFullDto.class);
        return userDto;
    }

    public UserDto convertToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    public User convertToEntity(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

    public ImageEntityDto convertToDto(ImageEntity imageEntity) {
        ImageEntityDto imageEntityDto = modelMapper.map(imageEntity, ImageEntityDto.class);
        return imageEntityDto;
    }

    public ImageEntity convertToEntity(ImageEntityDto imageEntityDto){
        ImageEntity imageEntity = modelMapper.map(imageEntityDto, ImageEntity.class);
        return imageEntity;
    }

    public User convertToEntity(UserCreateDto userCreateDto){
        User user = modelMapper.map(userCreateDto, User.class);
        return user;
    }

    public MessageDto convertToDto(Message message) {
        MessageDto messageDto = modelMapper.map(message, MessageDto.class);
        return messageDto;
    }

    public Message convertToEntity(MessageDtoFull messageDto){
        Message message = modelMapper.map(messageDto, Message.class);
        return message;
    }

    public DeviceDto convertToDto(Device device) {
        DeviceDto deviceDto = modelMapper.map(device, DeviceDto.class);
        return deviceDto;
    }

    public Device convertToEntity(DeviceDto deviceDto){
        Device device = modelMapper.map(deviceDto, Device.class);
        return device;
    }

    public ChannelDto convertToDto(Channel channel) {
        ChannelDto channelDto = modelMapper.map(channel, ChannelDto.class);
        return channelDto;
    }

    public Channel convertToEntity(ChannelDto channelDto){
        Channel channel = modelMapper.map(channelDto, Channel.class);
        return channel;
    }
}

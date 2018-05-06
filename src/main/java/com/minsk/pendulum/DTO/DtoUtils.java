package com.minsk.pendulum.DTO;

import com.minsk.pendulum.DTO.channel.ChannelDto;
import com.minsk.pendulum.DTO.device.DeviceDto;
import com.minsk.pendulum.DTO.message.MessageDto;
import com.minsk.pendulum.DTO.user.UserCreateDto;
import com.minsk.pendulum.DTO.user.UserDto;
import com.minsk.pendulum.model.Channel;
import com.minsk.pendulum.model.Device;
import com.minsk.pendulum.model.Message;
import com.minsk.pendulum.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DtoUtils {
    @Autowired
    private ModelMapper modelMapper;

    public UserDto convertToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    public User convertToEntity(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

    public User convertToEntity(UserCreateDto userCreateDto){
        User user = modelMapper.map(userCreateDto, User.class);
        return user;
    }

    public MessageDto convertToDto(Message message) {
        MessageDto messageDto = modelMapper.map(message, MessageDto.class);
        return messageDto;
    }

    public Message convertToEntity(MessageDto messageDto){
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

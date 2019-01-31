package com.hydee.hdsec.sqlDao;

import com.hydee.hdsec.entity.Message;
import com.hydee.hdsec.entity.MessageUser;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MessageDao {

    int insertMessage(Message message);

    int insertMessageUser(List<MessageUser> messages);

}

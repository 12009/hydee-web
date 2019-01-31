package com.hydee.hdsec.sqlDao.impl;

import com.hydee.hdsec.entity.Message;
import com.hydee.hdsec.entity.MessageUser;
import com.hydee.hdsec.sqlDao.MessageDao;
import com.hydee.hdsec.sqlDao.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("messageDao")
public class MessageDaoImpl extends BaseDao implements MessageDao {


    @Override
    public int insertMessage(Message message) {
        return (Integer) save("messageDaoMappper.insertMessage",message);
    }

    @Override
    public int insertMessageUser(List<MessageUser> messages) {
        batchInsert("messageDaoMappper.insertMessageUser",messages);
        return 0;
    }
}

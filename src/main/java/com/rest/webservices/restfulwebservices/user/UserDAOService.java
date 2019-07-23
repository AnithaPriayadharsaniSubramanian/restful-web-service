package com.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDAOService
{
    private static List<User> users = new ArrayList<>();
    private int userCount=3;
    static {
        users.add(new User(1,"Ani",new Date()));
        users.add(new User(2,"ni",new Date()));
        users.add(new User(3,"pr",new Date()));
    }

    public List<User> findAll(){
        return users;
    }

    public User saveUser(User user){
        if (user.getId() == null) {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User updateUser(User updateUser,int id){
        for(int i=0;i<=users.size();i++){
            User user= users.get(i);
            if(user.getId()==updateUser.getId()&& user.getId()==id){
                users.set(i,updateUser);
                return users.get(i);
            }

        }
            return null;
    }

    public User deleteUser(int id) {
        Iterator iterator= users.iterator();
        while(iterator.hasNext()){
            User user = (User) iterator.next();
            if(user.getId()==id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }

    public User findOne(int id){
        for(User user:users){
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }

}

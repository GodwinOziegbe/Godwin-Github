package com.BrathLia.SEOAnalyzer.Service;

import com.BrathLia.SEOAnalyzer.Auth.AuthenticatedUser;
import com.BrathLia.SEOAnalyzer.Auth.PasswordConfig;
import com.BrathLia.SEOAnalyzer.Entities.User;
import com.BrathLia.SEOAnalyzer.Entities.View;
import com.BrathLia.SEOAnalyzer.Repository.UserDao;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component

public class UserService implements ServiceInterface<User>, UserDetailsService {
    @Autowired
    private UserDao userDao;

    private List<User> userList;
    private List<View> viewList;
    private AuthenticatedUser authenticatedUser;

    @Autowired
    private PasswordConfig passwordConfig;

    @Override
    public void store(User user) {
        userDao.save(user);
    }


    @Override
    public void saveAll(List<User> userList){
        userDao.saveAll(userList);

    }

    public long getId() {
        List<User> userList = new ArrayList<>();
        userList = userDao.findAll();
        return userList.size();
    }


    /**
     * Method to locally store all users from database.
     * Used to iterate google report through all users
     *
     * ALSO CONTAINS MOCKUP USERS, THIS SHOULD BE DELETED LATER
     *
     * @param viewService
     */
    public void setLocalList(ViewService viewService){

        userList = new ArrayList<>();
        viewList = new ArrayList<>();
        userList = userDao.findAll();

        /**
         * Mockup feature, if no users exists in database this function will create 5 mock-users
         * and assign them a random view-id
         */
        if(userList.isEmpty()){
            Random rnd = new Random();

          for(int i = 0 ; i < 5; i++){

              User user = new User();
              Faker faker = new Faker();
              user.setFirstName(faker.name().firstName());
              user.setLastName(faker.name().lastName());
              user.setEmail(faker.pokemon().name() + "@gmail.com");
              user.setPassword(passwordConfig.passwordEncoder().encode("1234")); //uses Spring passwordencoder

              View view = new View();
              if(rnd.nextBoolean()) {           //Random view-Id True = Babyface, False = Brath
                  view.setView(62054068);
              } else {
                  view.setView(67337545);
              }

            //  viewService.store(view);
              view.setUser(user);

              viewList.add(view);
              userList.add(user);

          }
          saveAll(userList);
          viewService.saveAll(viewList);
        }

    }

    public List<User> getUserList(){
        return this.userList;

    }


    public List<User> userList(){
        return userDao.findAll();

    }

    public User findUserById(long id){
        return userDao.findUserByUserId(id);
    }

    /**
     * This method authenticates a user at login.
     *
     * @Override UserDetailsService
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findUserByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        authenticatedUser = new AuthenticatedUser(user); //This method stores the logged in user locally. Not ideal
        return new AuthenticatedUser(user);
    }

    /**
     * Returns the locally stored authenticated user
     *
     * Not ideal, used to get id in front-end part.
     * Needs to be redone
     * @return
     */
    public AuthenticatedUser getAuthenticatedUser() {
        return authenticatedUser;
    }
}

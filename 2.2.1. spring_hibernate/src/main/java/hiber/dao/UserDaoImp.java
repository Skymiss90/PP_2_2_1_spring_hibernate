package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }
   //Класс_1.Поле_в_классе_1_по_которому_JoinColumn.Поле_в_классе_2
   @Override
   public User getCarsUser(String model,int series) {
      TypedQuery <User> query =  sessionFactory.getCurrentSession().createQuery(
              "from User user where user.car.model=:model and user.car.series=:series",User.class);
      query.setParameter("model",model);
      query.setParameter("series",series);
      return query.getSingleResult();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}

package com.example.h2.test.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by victorsung.
 * Date: 2021/03/02
 * Time: 1:01 오전
 */
public class JpaMain {
    public static void main(String[] args) {
        // appllication loding 시점에 딱 하나만 만들어 놓아야 한다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 실제로 고객이 들어와서 일어나는 일괄적인 일을 할때 마다 EntityManager를 만들어야 한다.
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin(); // database 트랜잭션 시

        try {
            Member member = new Member();
            member.setId(3L);
            member.setName("jyp");

            em.persist(member);

            tx.commit();    // commit을 안하면 적용이 안된다.
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.clear();
        }
        emf.close();
    }
}

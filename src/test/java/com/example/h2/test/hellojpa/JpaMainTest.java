package com.example.h2.test.hellojpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by victorsung.
 * Date: 2021/03/02
 * Time: 1:27 오전
 */
class JpaMainTest {

    @Test
    @DisplayName("등록")
    void 등록(){
    	// given
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction ts = em.getTransaction();

        // when

    	// then
        try {
            ts.begin();

            Member member = new Member();
            member.setId(0L);
            member.setName("J");
            em.persist(member);

            ts.commit();
        }catch (Exception e){
            System.out.println("e = " + e);
            ts.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

    @Test
    @DisplayName("수정")
    void 수정(){
        // given
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction ts = em.getTransaction();

        // when

        // then
        try {
            ts.begin();

            Member member = em.find(Member.class, 0L);
            // JPA를 통해서 entity를 가지고 오면 JPA가 관리를 한다.
            // 즉 JPA가 이 값이 변경이 되었는지 안되었는지 transaction commit 시점에 check를 한다.
            member.setName("수정아");

            ts.commit();
        }catch (Exception e){
            System.out.println("e = " + e);
            ts.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

    @Test
    @DisplayName("조회")
    void 조회(){
        // given
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction ts = em.getTransaction();

        // when

        // then
        try {
            ts.begin();

            List<Member> resultList = em.createQuery("select m from Member as m", Member.class).getResultList();
            for (Member member : resultList) {
                System.out.println("member = " + member);
            }

            ts.commit();
        }catch (Exception e){
            System.out.println("e = " + e);
            ts.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
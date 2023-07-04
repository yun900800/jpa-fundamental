package org.jpa.fundamental.hibernate.generate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.jpa.fundamental.utils.AbstractTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GenerateTest extends AbstractTest {
    @Override
    protected Class[] entities() {
        return new Class[]{Hero.class};
    }

    @Test
    public void testInsertHero() {
        doInJPA( entityManager -> {
            Hero heroine = new Hero();
            heroine.setId( 1L );

            heroine.setFirstName( "Agustina" );
            heroine.setMiddleName1( "Raimunda" );
            heroine.setMiddleName2( "María" );
            heroine.setMiddleName3( "Saragossa" );
            heroine.setLastName( "Domènech" );

            entityManager.persist( heroine );
            LOGGER.info(()->"After entity persist action");
            entityManager.flush();

            assertEquals(
                    null,
                    heroine.getFullName()
            );
        } );

        doInJPA( entityManager -> {
            Hero heroine = entityManager.find( Hero.class, 1L );
            heroine.setMiddleName1( null );
            heroine.setMiddleName2( null );
            heroine.setMiddleName3( null );
            heroine.setLastName( "de Aragón" );

            LOGGER.info(()->"After entity update action");
            entityManager.flush();

            assertEquals(null, heroine.getFullName());
        } );
    }

    @Entity(name = "hero")
    @Table(name = "hero")
    public static class Hero {
        @Id
        private Long id;

        private String firstName;

        private String lastName;

        private String middleName1;

        private String middleName2;

        private String middleName3;

        private String middleName4;

        private String middleName5;

        @Generated( value = GenerationTime.ALWAYS )
        @Column(columnDefinition =
                "varchar(2000) GENERATED ALWAYS AS (CONCAT(" +
                        "    firstName, " +
                        "    middleName1," +
                        "    middleName2, " +
                        "    middleName3, " +
                        "    middleName4, " +
                        "    middleName5, " +
                        "    lastName) " +
                        ")", length = 2000)
        private String fullName;

        //Getters and setters omitted for brevity

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName){
            this.fullName = fullName;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getMiddleName1() {
            return middleName1;
        }

        public void setMiddleName1(String middleName1) {
            this.middleName1 = middleName1;
        }

        public String getMiddleName2() {
            return middleName2;
        }

        public void setMiddleName2(String middleName2) {
            this.middleName2 = middleName2;
        }

        public String getMiddleName3() {
            return middleName3;
        }

        public void setMiddleName3(String middleName3) {
            this.middleName3 = middleName3;
        }

        public String getMiddleName4() {
            return middleName4;
        }

        public void setMiddleName4(String middleName4) {
            this.middleName4 = middleName4;
        }

        public String getMiddleName5() {
            return middleName5;
        }

        public void setMiddleName5(String middleName5) {
            this.middleName5 = middleName5;
        }
    }
}

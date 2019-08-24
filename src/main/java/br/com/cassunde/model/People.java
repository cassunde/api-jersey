package br.com.cassunde.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.cassunde.util.Util;

@Entity
public class People implements CacheID {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    
    private String cpf;
    
    private Date dateBirth;
    
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "people", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones = new ArrayList<>();

    @Override
    public Integer getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;					
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getAge() {
		LocalDate dateBirthConveted = Util.toLocalDate(dateBirth);
		LocalDate now = LocalDate.now();
		Period period = Period.between(dateBirthConveted, now);
		return period.getYears();
	}

	public List<Phone> getPhones() {
		return phones;
	}
	
	public void addPhones(Phone phone) {
		phone.setPeople(this);
		this.phones.add(phone);
	}
	
	public void removePhones(Phone phone) {
		phone.setPeople(null);
		this.phones.remove(phone);
	}
	
	public void removePhonesById(Integer idPhone) {
		
		Phone phoneFinded = phones.stream().filter(p -> p.getId() == idPhone).findFirst().orElse(null);
		if( phoneFinded != null ) {
			phoneFinded.setPeople(null);
			this.phones.remove(phoneFinded);	
		}
	}

	public String toString() {
        return name;
    }
}

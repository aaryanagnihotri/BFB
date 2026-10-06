package com.wie.config;
import com.wie.model.*;
import com.wie.repository.UserRepository;
import java.time.Instant;
import java.util.Map;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
/** Dev only (SEED_DEMO=true). Password for all demo users: Demo@12345 */
@Component @ConditionalOnProperty(name = "app.seed-demo", havingValue = "true")
public class DemoDataSeeder implements CommandLineRunner {
  private final UserRepository users; private final PasswordEncoder enc;
  public DemoDataSeeder(UserRepository users, PasswordEncoder enc){ this.users=users; this.enc=enc; }
  public void run(String... args){
    if (users.count() > 0) return;
    add("Aryan Sharma","aryan@demo.com",Role.STUDENT,Map.of("college","Thapar","degree","B.Tech","branch","CSE","graduationYear","2026"));
    add("Meera Nair","recruiter@demo.com",Role.RECRUITER,Map.of("company","NeuraWorks","designation","Talent Lead","industry","AI","companySize","500-1000"));
    add("SkillForge Academy","agency@demo.com",Role.COURSE_AGENCY,Map.of("contactPerson","R. Singh","specialization","Applied AI","deliveryMode","Hybrid","location","Chandigarh"));
  }
  private void add(String n,String e,Role r,Map<String,Object> p){
    users.save(new User(null,n,e,enc.encode("Demo@12345"),r,true,p,Instant.now())); }
}

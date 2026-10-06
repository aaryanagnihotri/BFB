package com.wie.dashboard;
import com.wie.intelligence.IntelligenceService;
import com.wie.model.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
/** Access is enforced in SecurityConfig by URL prefix (STUDENT / RECRUITER / COURSE_AGENCY). */
@RestController @RequestMapping("/api")
public class DashboardController {
  private final IntelligenceService intel;
  public DashboardController(IntelligenceService intel){ this.intel=intel; }

  @GetMapping("/student/dashboard")
  public Map<String,Object> student(@AuthenticationPrincipal User u){
    return Map.of("greetingName", u.name(), "futureReadiness", 72,
      "cards", Map.of("skillStrength",81,"industryAlignment",68,"adaptability",74,"emergingSkillExposure",29),
      "skills", List.of(Map.of("name","Python","level",92), Map.of("name","Machine Learning","level",78),
        Map.of("name","OpenCV","level",70), Map.of("name","MLOps","level",32), Map.of("name","Edge AI","level",24)),
      "interventions", intel.interventionsFor("Edge AI")); }

  @GetMapping("/recruiter/dashboard")
  public Map<String,Object> recruiter(@AuthenticationPrincipal User u){
    return Map.of("company", u.profile().get("company"), "openPositions", 14, "talentPipeline", 238,
      "riskRadar", intel.forecasts(), "interventions", intel.interventionsFor("Edge AI")); }

  @GetMapping("/agency/dashboard")
  public Map<String,Object> agency(@AuthenticationPrincipal User u){
    return Map.of("agency", u.name(), "highDemand", intel.forecasts(),
      "topOpportunity", Map.of("skill","Edge AI","demandGrowthPct",43,"potentialLearners",780,"companiesHiring",14,"competition","LOW")); }
}

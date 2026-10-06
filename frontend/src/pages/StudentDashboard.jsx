import useDashboard from '../components/useDashboard'; import { Spinner, Notice } from '../components/States';
import ReadinessScore from '../components/ReadinessScore'; import SkillBar from '../components/SkillBar'; import StatCard from '../components/StatCard'; import InterventionCards from '../components/InterventionCards';
export default function StudentDashboard() {
  const { data: d, error } = useDashboard('student');
  if (error) return <Notice title="Couldn't load dashboard" text={error}/>; if (!d) return <Spinner/>;
  const c = d.cards;
  return (<div className="space-y-8"><div className="flex flex-wrap items-center gap-8">
    <ReadinessScore value={d.futureReadiness}/><div><h1 className="text-2xl font-semibold">Good day, {d.greetingName.split(' ')[0]} 👋</h1>
    <p className="text-slate-400">Here's your future workforce readiness.</p></div></div>
    <div className="grid grid-cols-2 gap-4 lg:grid-cols-4"><StatCard i={0} label="Skill Strength" value={c.skillStrength} suffix="%"/><StatCard i={1} label="Industry Alignment" value={c.industryAlignment} suffix="%"/>
      <StatCard i={2} label="Adaptability" value={c.adaptability} suffix="%"/><StatCard i={3} label="Emerging Skill Exposure" value={c.emergingSkillExposure} suffix="%"/></div>
    <section className="rounded-2xl bg-card p-5"><h2 className="mb-3 font-semibold">Your skills</h2>{d.skills.map(s => <SkillBar key={s.name} {...s}/>)}</section>
    <section><h2 className="mb-3 font-semibold">Recommended interventions</h2><InterventionCards items={d.interventions}/></section></div>);
}

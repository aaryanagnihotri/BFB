import { useEffect, useState } from 'react'; import { dashboard } from '../services/authService';
export default function useDashboard(path) {
  const [s, set] = useState({ data: null, error: null });
  useEffect(() => { dashboard(path).then(data => set({ data, error: null }))
    .catch(e => set({ data: null, error: e.response ? (e.response.data?.message || 'Request failed') : 'Network error. Is the API running?' })); }, [path]);
  return s;
}

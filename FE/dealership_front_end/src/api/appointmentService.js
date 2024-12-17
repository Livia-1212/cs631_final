import axios from 'axios';

const API_BASE = 'http://localhost:8080/api';

export const getAppointments = async () => {
  const response = await axios.get(`${API_BASE}/appointments`);
  return response.data;
};

export const createAppointment = async (appointment) => {
  const response = await axios.post(`${API_BASE}/appointments`, appointment);
  return response.data;
};

export const getServicePackages = async () => {
  const response = await axios.get(`${API_BASE}/service-packages`);
  return response.data;
};

export async function updateAppointment(id, updatedData) {
  const response = await fetch(`${API_BASE}/service-details/update`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(updatedData),
  });
  if (!response.ok) {
    throw new Error('Failed to update appointment');
  }
}

export const getAppointmentById = async (id) => {
  const response = await fetch(`${API_BASE}/appointments/${id}`);
  if (!response.ok) {
    throw new Error('Failed to fetch appointment details');
  }
  return await response.json();
};

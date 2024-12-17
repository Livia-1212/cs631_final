import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

export const createSale = (sale) => {
  return axios.post(`${API_BASE_URL}/sales`, sale);
};

export const getVehicleByVin = async (vin) => {
  return await axios.get(`${API_BASE_URL}/vehicles/vin/${vin}`);
};

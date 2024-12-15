import React, { useState, useEffect } from 'react';
import { getAppointments } from '../../api/appointmentService';
import AppointmentFilter from './AppointmentFilter';

const AppointmentList = () => {
  const [appointments, setAppointments] = useState([]);
  const [filteredAppointments, setFilteredAppointments] = useState([]);

  useEffect(() => {
    async function fetchAppointments() {
      const data = await getAppointments();
      const sortedData = data.sort((a, b) => a.appointmentId - b.appointmentId);
      setAppointments(sortedData);
      setFilteredAppointments(sortedData);
    }
    fetchAppointments();
  }, []);

  const handleFilter = (criteria) => {
    const filtered = appointments.filter((appointment) =>
      appointment.date.includes(criteria.date)
    );
    setFilteredAppointments(filtered);
  };

  return (
    <div>
      <h2>Upcoming Car Service Appointments</h2>
      <AppointmentFilter onFilter={handleFilter} />
      <table
        border="1"
        cellPadding="8"
        style={{ width: '100%', marginTop: '16px' }}
      >
        <thead>
          <tr>
            <th>Appointment ID</th>
            <th>Status</th>
            <th>Scheduled Time</th>
            <th>Estimated Time (mins)</th>
            <th>VIN</th>
            <th>Customer Name</th>
            <th>Package Name</th>
          </tr>
        </thead>
        <tbody>
          {filteredAppointments.map((appointment) => (
            <tr key={appointment.appointmentId}>
              <td>{appointment.appointmentId}</td>
              <td>{appointment.appointmentStatus}</td>
              <td>{appointment.scheduledTime}</td>
              <td>{appointment.estimatedTime}</td>
              <td>{appointment.vin}</td>
              <td>
                {appointment.firstName} {appointment.lastName}
              </td>
              <td>{appointment.packageName}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AppointmentList;

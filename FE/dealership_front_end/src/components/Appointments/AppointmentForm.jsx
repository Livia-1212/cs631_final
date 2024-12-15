import React, { useState } from 'react';
import { createAppointment } from '../../api/appointmentService';
import ServicePackageDetails from './ServicePackageDetails';
import { useNavigate } from 'react-router-dom';

const AppointmentForm = () => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [vehicleVin, setVehicleVin] = useState('');
  const [scheduledTime, setScheduledTime] = useState('');
  const [estimatedTime, setEstimatedTime] = useState('');
  const [selectedPackage, setSelectedPackage] = useState('');

  const navigate = useNavigate();
  const handleSubmit = async (e) => {
    e.preventDefault();
    const appointment = {
      firstName,
      lastName,
      vin: vehicleVin,
      scheduledTime,
      estimatedTime: parseInt(estimatedTime, 10),
      servicePackage: selectedPackage,
    };
    try {
      console.log(appointment);
      await createAppointment(appointment);
      alert('Appointment Created Successfully');
      navigate(`/service-appointments`); // Redirect to appointment list page
    } catch (error) {
      console.error('Error creating appointment:', error);
      alert('Failed to create appointment. Please try again.');
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Create New Appointment</h2>

      {/* Customer Details */}
      <label>
        First Name:
        <input
          type="text"
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
          required
        />
      </label>
      <label>
        Last Name:
        <input
          type="text"
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
          required
        />
      </label>

      {/* Vehicle Details */}
      <label>
        Vehicle VIN:
        <input
          type="text"
          value={vehicleVin}
          onChange={(e) => setVehicleVin(e.target.value)}
          required
        />
      </label>

      {/* Appointment Details */}
      <label>
        Scheduled Time:
        <input
          type="datetime-local"
          value={scheduledTime}
          onChange={(e) => setScheduledTime(e.target.value)}
          required
        />
      </label>
      <label>
        Estimated Time (minutes):
        <input
          type="number"
          value={estimatedTime}
          onChange={(e) => setEstimatedTime(e.target.value)}
          min="1"
          required
        />
      </label>

      {/* Service Package Selection */}
      <ServicePackageDetails onSelectPackage={setSelectedPackage} />

      {/* Submit Button */}
      <button type="submit">Create Appointment</button>
    </form>
  );
};

export default AppointmentForm;

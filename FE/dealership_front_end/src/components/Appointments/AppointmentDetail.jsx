import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import {
  getAppointmentById,
  updateAppointment,
} from '../../api/appointmentService';

const AppointmentDetail = () => {
  const { id } = useParams(); // Get appointment ID from route
  const navigate = useNavigate(); // For navigation
  const [appointment, setAppointment] = useState(null);
  const [isEditing, setIsEditing] = useState(false); // Toggle edit mode
  const [editedAppointment, setEditedAppointment] = useState({}); // Editable form state

  useEffect(() => {
    async function fetchAppointment() {
      const data = await getAppointmentById(id); // API call to fetch details
      setAppointment(data);
      setEditedAppointment(data);
    }
    fetchAppointment();
  }, [id]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setEditedAppointment((prev) => ({ ...prev, [name]: value }));
  };

  const handleSave = async () => {
    try {
      console.log(editedAppointment);
      await updateAppointment(id, editedAppointment); // Save updated details
      setAppointment(editedAppointment); // Update UI
      setIsEditing(false);
      alert('Appointment updated successfully!');
    } catch (error) {
      alert('Error updating appointment: ' + error.message);
    }
  };

  const handleGoBack = () => {
    navigate(-1); // Go back to the previous page
  };

  if (!appointment) {
    return <p>Loading appointment details...</p>;
  }

  return (
    <div>
      <h2>Appointment Details</h2>
      {isEditing ? (
        <form>
          <label>
            <strong>Status:</strong>
            <input
              type="text"
              name="appointmentStatus"
              value={editedAppointment.appointmentStatus || ''}
              onChange={handleInputChange}
            />
          </label>
          <label>
            <strong>Scheduled Time:</strong>
            <input
              type="datetime-local"
              name="scheduledTime"
              value={editedAppointment.scheduledTime || ''}
              onChange={handleInputChange}
            />
          </label>
          <label>
            <strong>Estimated Time:</strong>
            <input
              type="number"
              name="estimatedTime"
              value={editedAppointment.estimatedTime || ''}
              onChange={handleInputChange}
            />
          </label>
          <label>
            <strong>Labor Hours:</strong>
            <input
              type="number"
              name="laborHours"
              value={editedAppointment.laborHours || ''}
              onChange={handleInputChange}
            />
          </label>
          <label>
            <strong>Total Cost:</strong>
            <input
              type="number"
              name="totalCost"
              value={editedAppointment.totalCost || ''}
              onChange={handleInputChange}
            />
          </label>
          <button type="button" onClick={handleSave}>
            Save
          </button>
          <button type="button" onClick={() => setIsEditing(false)}>
            Cancel
          </button>
        </form>
      ) : (
        <>
          <p>
            <strong>Appointment ID:</strong> {appointment.appointmentId}
          </p>
          <p>
            <strong>Status:</strong> {appointment.appointmentStatus}
          </p>
          <p>
            <strong>Scheduled Time:</strong> {appointment.scheduledTime}
          </p>
          <p>
            <strong>Estimated Time:</strong> {appointment.estimatedTime}
          </p>
          <p>
            <strong>VIN:</strong> {appointment.vin}
          </p>
          <p>
            <strong>Service Package:</strong> {appointment.packageName}
          </p>
          <p>
            <strong>Arrival Time:</strong> {appointment.arrivalTime}
          </p>
          <p>
            <strong>Customer Name:</strong> {appointment.customerName}
          </p>
          <p>
            <strong>Labor Hours:</strong> {appointment.laborHours}
          </p>
          <p>
            <strong>Pick Up Time:</strong> {appointment.pickUpTime}
          </p>
          <p>
            <strong>Service Performed:</strong> {appointment.servicePerformed}
          </p>
          <p>
            <strong>Total Cost:</strong> {appointment.totalCost}
          </p>
          <button onClick={() => setIsEditing(true)}>Edit</button>
        </>
      )}
      <button onClick={handleGoBack}>Go Back</button>
    </div>
  );
};

export default AppointmentDetail;

import React, { useEffect, useState } from "react";
import './app.css';

interface User {
  id:string,
  firstName: string,
  lastName: string,
  email: string
}
const App: React.FC = () => {

  const [departments, setDepartments] = useState<string[]>([]);
  const [department, setDepartment] = useState<string>("");
  const [users, setUsers] = useState<User[]>([]);
  const fetchDepartments = async () => {
    try {
      const url = "http://localhost:8080/api/v1/departments";
      const resp = await fetch(url);
      const data = await resp.json();
      console.log(data);
      console.log("hi");
      setDepartments(data);
    } catch (error) {
      console.log("error while fetching the departments");
    }
  };
  const fetchUsers = async (dept:string) => {
    try {
    const url = `http://localhost:8080/api/v1/users?department=${encodeURIComponent(dept)}`;
    const resp = await fetch(url);
    const data = await resp.json();
    console.log(data);
    setUsers(data);
    } catch (error) {
      console.log("error while fetching users");
    }
  }
  useEffect(() => {
    fetchDepartments();
  }, [])
  useEffect(() => {
    fetchUsers(department);
  })
return (
  <div className="container">
    <h2>Select Department</h2>
    <select value={department} onChange={(e) => setDepartment(e.target.value)}>
      <option value="">--Select Department--</option>
      {departments.map((dep) => (
        <option key={dep} value={dep}>{dep}</option>
      ))}
    </select>

    <h3>User List</h3>
    <table className="user-table">
      <thead>
        <tr>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Email</th>
        </tr>
      </thead>
      <tbody>
        {users.map((user: any) => (
          <tr key={user.id}>
            <td>{user.firstName}</td>
            <td>{user.lastName}</td>
            <td>{user.email}</td>
          </tr>
        ))}
      </tbody>
    </table>
  </div>
);

}
export default App;
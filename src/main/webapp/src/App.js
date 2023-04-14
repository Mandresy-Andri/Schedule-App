import './App.css';
import React from 'react'
import NavigationBar from './components/NavigationBar';
import TaskComponent from './components/TaskComponent';
import Welcome from './components/Welcome';
import AddTaskComponent from './components/AddTaskComponent';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';

export default function App() {
  return (
    <Router>
      <NavigationBar/>
      <Routes>
        <Route path='/' exact element={<Welcome/>}/>
        <Route path='add' exact element={<AddTaskComponent/>}/>
        <Route path='list' exact element={<TaskComponent/>}/>
        <Route path='edit/:id' exact element={<AddTaskComponent/>}/>
      </Routes>
    </Router>
  );
}

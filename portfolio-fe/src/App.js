// import React from 'react';
// import Gallery from './Gallery';
//
// function App() {
//     return (
//         <div className="bg-black min-h-screen">
//             <Gallery />
//         </div>
//     );
// }
//
// export default App;
import React from 'react';
import Gallery from './Gallery';
import './App.css';

function App() {
    return (
        <div className="app-container">
            <header className="app-header">
                <nav className="nav-bar">
                    <span>HOME</span>
                    <span>SERVICES</span>
                    <span>CONTACT</span>
                </nav>
            </header>

            <main className="gallery-section">
                <Gallery />
            </main>
        </div>
    );
}

export default App;
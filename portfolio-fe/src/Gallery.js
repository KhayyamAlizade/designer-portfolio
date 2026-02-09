import './Gallery.css';
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const GROUP_COUNT = 8;
const ITEMS_PER_GROUP = 15;
const COLUMNS_PER_ROW = 3;
const ROWS_PER_GROUP = 5;

const GROUP_ROW_HEIGHTS = [
    [700, 550, 340, 280, 320],
    [410, 250, 330, 270, 300],
    [285, 270, 350, 290, 310],
    [305, 255, 345, 285, 315],
    [290, 260, 335, 275, 305],
    [300, 240, 360, 295, 325],
    [315, 250, 320, 265, 310],
    [305, 265, 340, 270, 300]
];

const COLUMN_WIDTHS = [
    ['45%', '35%', '20%'],
    ['40%', '30%', '30%'],
    ['30%', '40%', '30%'],
    ['24%', '43%', '33%'],
    ['32%', '36%', '32%']
];

const Gallery = () => {
    const [mediaItems, setMediaItems] = useState([]);
    const [selectedImage, setSelectedImage] = useState(null);
    const [currentIndex, setCurrentIndex] = useState(0);

    useEffect(() => {
        axios.get('/api/media/page/HOME/images')
            .then(response => setMediaItems(response.data))
            .catch(error => console.error('Veri alınamadı:', error));
    }, []);

    // Escape tuşu ile kapatma
    useEffect(() => {
        const handleKeyDown = (e) => {
            if (e.key === 'Escape') {
                handleClose();
            }
            if (selectedImage) {
                if (e.key === 'ArrowLeft') {
                    handlePrevious();
                }
                if (e.key === 'ArrowRight') {
                    handleNext();
                }
            }
        };

        document.addEventListener('keydown', handleKeyDown);
        return () => document.removeEventListener('keydown', handleKeyDown);
    }, [selectedImage]);

    // MediaItem'ları group, row, column'a göre organize et
    const groupedItems = Array.from({ length: GROUP_COUNT }, () =>
        Array.from({ length: ROWS_PER_GROUP }, () =>
            Array.from({ length: COLUMNS_PER_ROW }, () => null)
        )
    );

    mediaItems.forEach(item => {
        const { groupIndex, rowIndex, columnIndex } = item;
        if (
            groupIndex < GROUP_COUNT &&
            rowIndex < ROWS_PER_GROUP &&
            columnIndex < COLUMNS_PER_ROW
        ) {
            groupedItems[groupIndex][rowIndex][columnIndex] = item;
        }
    });

    // Tüm mevcut medya öğelerini düz bir liste halinde al (navigation için)
    const allMediaItems = mediaItems.filter(item => item !== null);

    const handleImageClick = (image) => {
        if (image) {
            const index = allMediaItems.findIndex(item =>
                item.groupIndex === image.groupIndex &&
                item.rowIndex === image.rowIndex &&
                item.columnIndex === image.columnIndex
            );
            setCurrentIndex(index);
            setSelectedImage(image);
            // Body scroll'unu engelle
            document.body.style.overflow = 'hidden';
        }
    };

    const handleClose = () => {
        setSelectedImage(null);
        // Body scroll'unu geri aç
        document.body.style.overflow = 'auto';
    };

    const handleNext = () => {
        if (currentIndex < allMediaItems.length - 1) {
            const nextIndex = currentIndex + 1;
            setCurrentIndex(nextIndex);
            setSelectedImage(allMediaItems[nextIndex]);
        }
    };

    const handlePrevious = () => {
        if (currentIndex > 0) {
            const prevIndex = currentIndex - 1;
            setCurrentIndex(prevIndex);
            setSelectedImage(allMediaItems[prevIndex]);
        }
    };

    const renderGroup = (groupIndex, groupData) => {
        const rows = [];
        for (let row = 0; row < ROWS_PER_GROUP; row++) {
            const height = GROUP_ROW_HEIGHTS[groupIndex % GROUP_ROW_HEIGHTS.length][row];
            const widths = COLUMN_WIDTHS[row % COLUMN_WIDTHS.length];

            rows.push(
                <div key={`g-${groupIndex}-r-${row}`} className="image-row" style={{ height: `${height}px` }}>
                    {groupData[row].map((item, colIndex) => (
                        <div
                            key={`g-${groupIndex}-r-${row}-c-${colIndex}`}
                            className="image-cell"
                            style={{ width: widths[colIndex], height: '100%' }}
                            onClick={() => handleImageClick(item)}
                        >
                            {item ? (
                                <img
                                    src={`${item.proxy || ''}/${item.imagePath}/${item.fileName}`}
                                    alt={item.title}
                                    className="image-item"
                                    loading="lazy"
                                />
                            ) : (
                                <div className="empty-cell"></div>
                            )}
                        </div>
                    ))}
                </div>
            );
        }

        return <div key={`group-${groupIndex}`} className="image-group">{rows}</div>;
    };

    return (
        <div className="gallery-container">
            {groupedItems.map((group, index) => renderGroup(index, group))}

            {/* Fullscreen Modal */}
            {selectedImage && (
                <div className="fullscreen-modal" onClick={handleClose}>
                    <div className="modal-background"></div>

                    {/* Navigation Arrows */}
                    {currentIndex > 0 && (
                        <button
                            className="nav-arrow nav-arrow-left"
                            onClick={(e) => {
                                e.stopPropagation();
                                handlePrevious();
                            }}
                        >
                            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M15 18L9 12L15 6" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                            </svg>
                        </button>
                    )}

                    {currentIndex < allMediaItems.length - 1 && (
                        <button
                            className="nav-arrow nav-arrow-right"
                            onClick={(e) => {
                                e.stopPropagation();
                                handleNext();
                            }}
                        >
                            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M9 18L15 12L9 6" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                            </svg>
                        </button>
                    )}

                    {/* Close Button */}
                    <button className="fullscreen-close" onClick={handleClose}>
                        <svg width="32" height="32" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M18 6L6 18M6 6L18 18" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                        </svg>
                    </button>

                    {/* Main Content */}
                    <div className="fullscreen-content" onClick={(e) => e.stopPropagation()}>
                        <div className="fullscreen-image-container">
                            <img
                                src={`${selectedImage.proxy || ''}/${selectedImage.imagePath}/${selectedImage.fileName}`}
                                alt={selectedImage.title}
                                className="fullscreen-image"
                            />
                        </div>

                        {/* Project Info */}
                        {/*<div className="project-info">*/}
                        {/*    <h2 className="project-title">{selectedImage.title}</h2>*/}
                        {/*</div>*/}
                    </div>
                </div>
            )}
        </div>
    );
};

export default Gallery;
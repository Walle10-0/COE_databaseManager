import React from 'react';
import { IconButton } from '@mui/material';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';

const ExpandCollapseButton = ({ isOpen, onClick }) => {
  return (
    <IconButton aria-label="expand row" size="small" onClick={onClick}>
      {isOpen ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
    </IconButton>
  );
};

export default ExpandCollapseButton;
import {createTheme} from '@mui/material/styles';

export const mainTheme = createTheme({
    palette: {
        mode: 'light',
        primary: {
            main: '#A6A6A6',
        },
        secondary: {
            main: '#F57',
        },
    },
    /*
    components: {
        MuiButton: {
             styleOverrides: {
                 outlined: {
                     borderColor: "#f50057"
                 }
             }
        },
    },

     */
    typography: {
        h4: {
            fontWeight: 100,
        },
    },
});
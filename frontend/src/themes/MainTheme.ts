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
    spacing: (spacer: number) => `${0.2 * spacer}rem`,

    /*
    components: {
        MuiOutlinedInput: {
        },
    },
    typography: {
        h4: {
            fontWeight: 100,
        },
    },

     */
});
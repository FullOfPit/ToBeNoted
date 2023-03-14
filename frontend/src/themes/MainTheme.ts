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

    components: {
        MuiAppBar: {
            styleOverrides: {
                root: {
                    background: "#FFFCF9"
                }
            }
        },
        MuiIconButton: {
            variants: [
                {
                    props: {"aria-label": "round shadow"},
                    style: {
                        padding: 1,
                        borderRadius: 50,
                        boxShadow: "2px 2px 3px 1px"
                    }
                },
            ]
        },
    },
    typography: {
        h4: {
            fontWeight: 100,
        },
    },

});